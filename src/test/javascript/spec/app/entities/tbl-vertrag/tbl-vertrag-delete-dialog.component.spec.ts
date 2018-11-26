/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblVertragDeleteDialogComponent } from 'app/entities/tbl-vertrag/tbl-vertrag-delete-dialog.component';
import { TblVertragService } from 'app/entities/tbl-vertrag/tbl-vertrag.service';

describe('Component Tests', () => {
    describe('TblVertrag Management Delete Component', () => {
        let comp: TblVertragDeleteDialogComponent;
        let fixture: ComponentFixture<TblVertragDeleteDialogComponent>;
        let service: TblVertragService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblVertragDeleteDialogComponent]
            })
                .overrideTemplate(TblVertragDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TblVertragDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblVertragService);
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
