/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { ArbeitsbelegDeleteDialogComponent } from 'app/entities/arbeitsbeleg/arbeitsbeleg-delete-dialog.component';
import { ArbeitsbelegService } from 'app/entities/arbeitsbeleg/arbeitsbeleg.service';

describe('Component Tests', () => {
    describe('Arbeitsbeleg Management Delete Component', () => {
        let comp: ArbeitsbelegDeleteDialogComponent;
        let fixture: ComponentFixture<ArbeitsbelegDeleteDialogComponent>;
        let service: ArbeitsbelegService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [ArbeitsbelegDeleteDialogComponent]
            })
                .overrideTemplate(ArbeitsbelegDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ArbeitsbelegDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ArbeitsbelegService);
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
