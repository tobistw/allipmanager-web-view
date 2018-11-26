/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblAspDeleteDialogComponent } from 'app/entities/tbl-asp/tbl-asp-delete-dialog.component';
import { TblAspService } from 'app/entities/tbl-asp/tbl-asp.service';

describe('Component Tests', () => {
    describe('TblAsp Management Delete Component', () => {
        let comp: TblAspDeleteDialogComponent;
        let fixture: ComponentFixture<TblAspDeleteDialogComponent>;
        let service: TblAspService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblAspDeleteDialogComponent]
            })
                .overrideTemplate(TblAspDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblAspDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblAspService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
